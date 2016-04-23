(ns analytics.trackers.stop
  (:use [analytics.services.ops])
  (:require [analytics.core :refer :all]
            [analytics.utils :as util])
  (:gen-class))


(defmethod track-op "stop" [data]
  (println "Create a user if needed")
  ;; create or ping a session
  (println "Stopping a stream with data" data)

  ;; Track Event
  (let [op-id
        (add-op!
          "stop"
          (:site-id data)
          (or (:user-id data) (:anonymous-id data))
          (:session-id data)
          (:asset-id data)
          (:channel data)
          (or (:page data) (:name data) (:screen data))
          (:event data))]
    (println "Calculating the duration from asset-id and generate stats")
    (println "Save properties")
    op-id))
