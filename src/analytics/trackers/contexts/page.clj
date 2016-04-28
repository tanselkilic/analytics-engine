(ns analytics.trackers.contexts.page
  (:use [analytics.services.contexts.page])
  (:require [analytics.core :refer :all])
  (:gen-class))


(defmethod track-context :page [p-context op-id data]
  (let [context (second p-context)]
    (add-page-context!
      op-id
      (:type data)
      (:site-id data)
      (or (:user-id data) (:anonymous-id data))
      (:session-id data)
      (:page data)
      (:event data)
      (:type (:referrer context))
      (:url (:referrer context))
      (:domain (:referrer context))
      (:title context)
      (:url context))))
