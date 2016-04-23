(ns analytics.trackers.contexts.device
  (:use [analytics.services.contexts.device])
  (:require [analytics.core :refer :all]
            [analytics.utils :as util])
  (:gen-class))


(defmethod track-context :device [p-context op-id data]
  (let [context (second p-context)]
    (add-device-context!
      op-id
      (:type data)
      (:site-id data)
      (or (:user-id data) (:anonymous-id data))
      (:session-id data)
      (:page data)
      (:event data)
      (:id context)
      (:manufacturer context)
      (:model context)
      (:name context))))
