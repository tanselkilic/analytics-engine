(ns analytics.utils
  (:import [java.util UUID Calendar])
  (:gen-class))


(defn uuid []
  (.toString (UUID/randomUUID)))


(defn diff-in-secs [cal1 cal2]
  (/ (- (.getTimeInMillis cal1) (.getTimeInMillis cal2)) (* 1000.0)))


(defn secs-until-date [compare-date]
  (if (some? compare-date)
    (let [now (java.util.Date.)]
      (let [compare-cal (Calendar/getInstance)
            now-cal (Calendar/getInstance)]
        (.setTime now-cal now)
        (.setTime compare-cal compare-date)
        (diff-in-secs now-cal compare-cal)))
    0))
