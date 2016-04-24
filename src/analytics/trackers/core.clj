(ns analytics.trackers.core
  (:require [analytics.core :refer :all]
            [analytics.channels :as chn]
            [analytics.services.users :as users]
            [analytics.services.ops :as ops]
            [analytics.services.sessions :as sessions]
            [analytics.utils :as util])
  (:gen-class))

(defn user-session-prep [data]
  ;; Creating a user record if needed
  (users/insert-user-if-needed
    (:anonymous-id data)
    (or (:user-id data) (:anonymous-id data))
    (:site-id data)
    (:channel data))

  ;; Creating a session record if needed, pinging elsewise
  (let [session-id (:session-id data)
         session (sessions/get-session session-id)]
    (if (nil? session)
      (do
        (sessions/add-session!
          session-id
          (:site-id data)
          (:user-id data)
          (:channel data)
          (:ip (:context data))
          (:locale (:context data))
          (:user-agent (:context data)))
        (sessions/ping-session!
          session-id)

        ;; record this op as new session (land)
        (ops/add-op!
          "land"
          (:site-id data)
          (or (:user-id data) (:anonymous-id data))
          (:session-id data)
          (or (:hash-code data) (util/uuid))
          (:channel data)
          (:page data)
          nil))))

  ;; Track metrics
  (chn/>! chn/chn-metrics {:type "user" :data data})
  (chn/>! chn/chn-metrics {:type "session" :data data}))

