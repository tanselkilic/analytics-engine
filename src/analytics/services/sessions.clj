(ns analytics.services.sessions
  (:require [analytics.core :refer :all]
            [analytics.utils :as util]
            [conman.core :as conman])
  (:import  [java.util Date])
  (:gen-class))


(defn add-session!
  [session-id site-id user-id channel
   ip locale user-agent]
  (let [date (Date.)
        prev-session-count
        (or (:num (db-count-sessions-by-user
                    {:site_id site-id
                     :user_id user-id}))
            0)]
    (db-create-session!
      {:session_id session-id
       :site_id site-id
       :user_id user-id
       :prev_session_count prev-session-count
       :channel channel
       :ip ip
       :locale locale
       :user_agent user-agent
       :created_at date
       :updated_at date})))


(defn delete-session-by-id!
  [session-id]
  (db-delete-session! {:session_id session-id}))


(defn ping-session!
  "Updates the updated_at time of session with current date"
  [session-id]
  (db-update-session-updated-date!
    {:updated_at (Date.)
     :session_id session-id}))


(defn session-expired? [session-id]
  (let [last-updated-at
        (:updated_at (db-session-last-updated-at
          {:session_id session-id}))
        secs-since-updated
        (util/secs-until-date last-updated-at)]

    ;; expires in 5 minutes
    (> secs-since-updated 300)))


(defn bounced?
  "Checks if a session bounced or not"
  [session-id]
  (let [session (db-get-session
                  {:session_id session-id})]
    (and (session-expired? session-id)
         (= (:created_at session) (:updated_at session)))))


(defn first-visit?
  "Checks if this is the first visit of a visitor or not"
  [session-id]
  (let [session
        (db-get-session
          {:session_id session-id})]
    (= (:prev_session_count session) 0)))
