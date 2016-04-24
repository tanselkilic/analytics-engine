(ns analytics.trackers.event
  (:use [analytics.services.ops])
  (:require [analytics.core :refer :all]
            [analytics.trackers.core :as tc]
            [analytics.trackers.contexts.properties :as tp]
            [analytics.utils :as util])
  (:gen-class))


(defmethod track-op "event" [data]
  (tc/user-session-prep data)

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

    ;; Track Properties
    (tp/track-properties op-id data)
    op-id))
