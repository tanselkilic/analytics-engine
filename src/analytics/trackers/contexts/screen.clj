(ns analytics.trackers.contexts.screen
  (:use [analytics.services.contexts.screen])
  (:require [analytics.core :refer :all]
            [analytics.utils :as util])
  (:gen-class))


(defmethod track-context :screen [p-context op-id data]
  (let [context (second p-context)]
    (add-screen-context!
      op-id
      (:type data)
      (:site-id data)
      (or (:user-id data) (:anonymous-id data))
      (:session-id data)
      (:page data)
      (:event data)
      (:height context)
      (:width context))))
