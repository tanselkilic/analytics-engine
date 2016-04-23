(ns analytics.trackers.screen
  (:use [analytics.services.ops])
  (:require [analytics.core :refer :all]
            [analytics.utils :as util])
  (:gen-class))

(defmethod track-op "screen" [data]
  (println "Create a user if needed")
  (println "Create or ping a session")

  ;; Track Screen
  (let [op-id
        (add-op!
          "screen"
          (:site-id data)
          (or (:user-id data) (:anonymous-id data))
          (:session-id data)
          (or (:hash-code data) (util/uuid))
          (:channel data)
          (or (:page data) (:name data) (:screen data))
          nil)]
    (println "Save properties")
    op-id))


