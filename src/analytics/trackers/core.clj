(ns analytics.trackers.core
  (:require [analytics.services.users :as users]
            [analytics.services.ops :as ops]
            [analytics.services.sessions :as sessions])
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

        ;; add this op as land
        (ops/add-op!
          "land"
          (:site-id data)
          (or (:user-id data) (:anonymous-id data))
          (:session-id data)
          (or (:hash-code data) (util/uuid))
          (:channel data)
          (:page data)
          nil)))))
