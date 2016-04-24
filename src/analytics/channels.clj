(ns analytics.channels
  (:require [clojure.core.async :as async]))


(defonce chn-metrics (async/chan 10))

(defn >! [channel data]
  (if (some? data)
    (async/go
     (async/>!
      channel data))))
