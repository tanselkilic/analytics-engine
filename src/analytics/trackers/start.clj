(ns analytics.trackers.start
  (:use [analytics.services.ops])
  (:require [analytics.core :refer :all]
            [analytics.utils :as util])
  (:gen-class))


(defmethod track-op "start" [data]
  (println "Create a user if needed")
    ;; create or ping a session
  (println "Starting a new stream with data" data)


  ;; Track Event
  (let [op-id
        (add-op!
          "start"
          (:site-id data)
          (or (:user-id data) (:anonymous-id data))
          (:session-id data)
          (:asset-id data)
          (:channel data)
          (or (:page data) (:name data) (:screen data))
          (:event data))]
    (println "Save properties")
    op-id))
