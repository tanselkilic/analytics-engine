(ns analytics.metrics.core
  (:require [analytics.channels :as chn]
            [clojure.core.async :as async]
            [analytics.services.metrics :as m]
            [analytics.env :as env])
  (:gen-class))

(def metrics-atom (atom nil))

;; create an async loop for metrics calculations
(defn metrics-loop
  "start this pool if it is not running"
  []
  (if (nil? @metrics-atom)
    (do
      (println "Starting Metrics loop.")
      (async/go-loop
        []
        (let [item (async/<! chn/chn-metrics)
              data (:data item)]
          (try
            (println "Got metric:" (:type item))
            (let [context (:type item)]
              (doseq [calc (env/metrics (keyword context))]
                (m/+! context data calc)))
            (catch Exception e (.printStackTrace e)))
          (recur)))
      (reset! metrics-atom "1")
      1)
    (do
      (println "Metrics loop still running")
      0)))

(metrics-loop)
