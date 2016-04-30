(ns analytics.trackers.start
  (:use [analytics.services.ops])
  (:require [analytics.core :refer :all]
            [analytics.trackers.core :as tc]
            [analytics.trackers.contexts.properties :as tp]
            [analytics.utils :as util])
  (:gen-class))


(defmethod track-op "start" [data]
  (tc/user-session-prep data)

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
    ;; Track Properties
    (tp/track-properties op-id data)

    ;; Track metrics
    (tc/track-metrics :start (assoc data :op-id op-id))

    op-id))
