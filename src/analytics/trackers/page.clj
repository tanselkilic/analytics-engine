(ns analytics.trackers.page
  (:use [analytics.services.ops])
  (:require [analytics.core :refer :all]
            [analytics.trackers.core :as tc]
            [analytics.utils :as util])
  (:gen-class))


(defmethod track-op "page" [data]
  (tc/user-session-prep data)

  ;; Track Page
  (let [op-id
        (add-op!
          "page"
          (:site-id data)
          (or (:user-id data) (:anonymous-id data))
          (:session-id data)
          (or (:hash-code data) (util/uuid))
          (:channel data)
          (:page data)
          nil)]
    (println "TODO: Save properties")
    op-id))

