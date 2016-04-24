(ns analytics.metrics.core
  (:require [analytics.channels :as chn]
            [clojure.core.async :as async]
            [analytics.metrics.event :as event]
            [analytics.metrics.page :as page]
            [analytics.metrics.screen :as screen]
            [analytics.metrics.session :as session]
            [analytics.metrics.start :as start]
            [analytics.metrics.stop :as stop]
            [analytics.metrics.user :as user])
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
          (println "Got metric:" (:type item))

          ;; TODO: redirect the metrics op.
          (case (:type item)
            "page" (page/metrics-ops data)
            "event" (event/metrics-ops data)
            "screen" (screen/metrics-ops data)
            "session" (session/metrics-ops data)
            "start" (start/metrics-ops data)
            "stop" (stop/metrics-ops data)
            "user" (user/metrics-ops data)
            "default")
          (recur)))
      (reset! metrics-atom "1"))))
