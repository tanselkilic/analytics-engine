(ns analytics.trackers.contexts.location
  (:use [analytics.services.contexts.location])
  (:require [analytics.core :refer :all]
            [analytics.utils :as util])
  (:gen-class))

(defn- ip2geo [ip]
  ;; TODO: implement this
  )


(defmethod track-context :location [p-context op-id data]
  (let [context (second p-context)
        loc_data (ip2geo (:ip (:context data)))]
    (add-location-context!
      op-id
      (:type data)
      (:site-id data)
      (or (:user-id data) (:anonymous-id data))
      (:session-id data)
      (:page data)
      (:event data)
      (or (:latitude context) (:latitude loc_data))
      (or (:longitude context) (:longitude loc_data))
      (or (:country_code context) (:country_code loc_data))
      (or (:city context) (:city loc_data))
      (or (:postal_code context) (:postal_code loc_data))
      (or (:organization context) (:organization loc_data)))))
