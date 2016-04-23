(ns analytics.trackers.page
  (:use [analytics.services.ops])
  (:require [analytics.core :refer :all]
            [analytics.utils :as util])
  (:gen-class))


(defmethod track-op "page" [data]
  (println "Create a user if needed")
  (println "Create or ping a session")

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
    (println "Save properties")
    op-id))

