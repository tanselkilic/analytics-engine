(ns analytics.trackers.stop
  (:use [analytics.services.ops])
  (:require [analytics.core :refer :all]
            [analytics.trackers.core :as tc]
            [analytics.trackers.contexts.properties :as tp]
            [analytics.utils :as util])
  (:gen-class))


(defmethod track-op "stop" [data]
  (tc/user-session-prep data)

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
    (println "TODO: Calculating the duration from asset-id and generate stats")

    ;; Track Properties
    (tp/track-properties op-id data)
    op-id))
