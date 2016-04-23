(ns analytics.trackers.identify
  (:require [analytics.core :refer :all]
            [analytics.utils :as util])
  (:gen-class))


(defmethod track-op "identify" [data]
  (println "Identify with data" data))
