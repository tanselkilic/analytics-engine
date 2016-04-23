(ns analytics.trackers.contexts.app
  (:use [analytics.services.contexts.app])
  (:require [analytics.core :refer :all]
            [analytics.utils :as util])
  (:gen-class))

(defmethod track-context :app [p-context op-id data]
  (let [context (second p-context)]
    (add-app-context!
      op-id
      (:type data)
      (:site-id data)
      (or (:user-id data) (:anonymous-id data))
      (:session-id data)
      (:page data)
      (:event data)
      (:name context)
      (:version context))))
