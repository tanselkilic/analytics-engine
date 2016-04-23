(ns analytics.trackers.contexts.network
  (:use [analytics.services.contexts.network])
  (:require [analytics.core :refer :all]
            [analytics.utils :as util])
  (:gen-class))


(defn- ip2geo [ip]
  ;; TODO: implement this
  )


(defmethod track-context :network [p-context op-id data]
  (let [context (second p-context)
        loc_data (ip2geo (:ip (:context data)))]
    (add-network-context!
      op-id
      (:type data)
      (:site-id data)
      (or (:user-id data) (:anonymous-id data))
      (:session-id data)
      (:page data)
      (:event data)
      (:carrier context)
      (or (:isp context) (:isp loc_data))
      (:bluetooth context)
      (:cellular context)
      (:wifi context))))
