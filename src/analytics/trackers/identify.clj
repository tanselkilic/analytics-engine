(ns analytics.trackers.identify
  (:require [analytics.core :refer :all]
            [analytics.services.users :as users]
            [analytics.services.ops :as ops]
            [analytics.services.user-traits :as traits]
            [analytics.utils :as util])
  (:gen-class))


(defmethod track-op "identify" [data]
  ;; Identifying user in users
  (users/identify-user!
    (:anonymous-id data)
    (:user-id data))

  ;; Now saving the user traits
  (let [traits-map (:traits data)]
    (traits/save-user-traits!
      (:site-id data)
      (:user-id data)
      traits-map))

  (ops/add-op!
    "identify"
    (:site-id data)
    (or (:user-id data) (:anonymous-id data))
    (:session-id data)
    (or (:hash-code data) (util/uuid))
    (:channel data)
    (:page data)
    nil))
