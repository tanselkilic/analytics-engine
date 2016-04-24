(ns analytics.trackers.screen
  (:use [analytics.services.ops])
  (:require [analytics.core :refer :all]
            [analytics.channels :as chn]
            [analytics.trackers.core :as tc]
            [analytics.trackers.contexts.properties :as tp]
            [analytics.utils :as util])
  (:gen-class))

(defmethod track-op "screen" [data]
  (tc/user-session-prep data)

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
    ;; Track Properties
    (tp/track-properties op-id data)

    ;; Track metrics
    (chn/>! chn/chn-metrics
           {:type "screen"
            :data (assoc data :op-id op-id)})

    op-id))


