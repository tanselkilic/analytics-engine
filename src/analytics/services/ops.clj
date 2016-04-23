(ns analytics.services.ops
  (:require [analytics.core :refer :all]
            [analytics.utils :as util]
            [conman.core :as conman])
  (:import  [java.util Date])
  (:gen-class))

(defn add-op!
  [op-type site-id user-id session-id
   hash-code channel page event]
  (try
    (db-create-op!
      {:op_type op-type
       :site_id site-id
       :user_id user-id
       :session_id session-id
       :hash_code hash-code
       :channel channel
       :page page
       :event event
       :created_at (Date.)})
    (catch Exception e (println (.getMessage e)))))


;; (add-op! "page" "914b36e4-a10d-4e13-bf35-df888bfcb1fa"
;;          "hebelek666@pismail.com" "session-id"
;;          "1212312312" "mobile" "Sign Up" nil)
