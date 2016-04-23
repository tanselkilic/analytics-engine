(ns analytics.trackers.event
  (:use [analytics.services.ops])
  (:require [analytics.core :refer :all]
            [analytics.utils :as util])
  (:gen-class))


(defmethod track-op "event" [data]
  (println "Create a user if needed")
  (println "Create or ping a session")

  ;; Track Event
  (let [op-id
        (add-op!
          "event"
          (:site-id data)
          (or (:user-id data) (:anonymous-id data))
          (:session-id data)
          (or (:hash-code data) (util/uuid))
          (:channel data)
          (or (:page data) (:name data) (:screen data))
          (:event data))]
    (println "Save properties")
    op-id))
