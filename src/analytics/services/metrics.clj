(ns analytics.services.metrics
  (:require [analytics.core :refer :all]
            [analytics.utils :as util]
            [digest :as digest])
  (:import  [java.util Date]
            [java.text SimpleDateFormat])
  (:gen-class))

(defn- str2num
  "Converts a string to md5 hex and then to a biginteger"
  [s]
  (BigInteger. (digest/md5 s) 16))


(defn- date-prefixes []
  (let [date (Date.)]
    [""
     (.format (SimpleDateFormat. "yyy") date)
     (.format (SimpleDateFormat. "yyy.MM") date)
     (.format (SimpleDateFormat. "yyy.MM.dd") date)
     (.format (SimpleDateFormat. "yyy.MM.dd HH") date)]))


(defn get-metric
  "Retrieves a metric row"
  [context metric-type date-prefix dim-name1
   dim-name2 dim-name3 dim-name4 dim-value1
   dim-value2 dim-value3 dim-value4]

  (db-get-metric
    {:context context
     :metric_type metric-type
     :date_prefix date-prefix
     :dim_name
     (str2num
       (str (or dim-name1 "")
            (or dim-name2 "")
            (or dim-name3 "")
            (or dim-name4 "")))
     :dim_value
     (str2num
       (str (or dim-value1 "")
            (or dim-value2 "")
            (or dim-value3 "")
            (or dim-value4 "")))}))


(defn increment!
  "Increment a metric value. Creates a new one if needed"
  [{:keys
   [context metric-type dim-name1 dim-name2
    dim-name3 dim-name4 dim-value1 dim-value2
    dim-value3 dim-value4 incr-val]}]
  (let [date-prefixes (date-prefixes)
        p-metric-type (or metric-type "inc")
        p-incr-val (or incr-val 1)]
    (doseq [date-prefix date-prefixes]

      (println "dim-value1:" dim-value1)

      (let [metric
            (get-metric
              context p-metric-type
              date-prefix dim-name1
              dim-name2 dim-name3
              dim-name4 dim-value1
              dim-value2 dim-value3
              dim-value4)]
        (if (nil? metric)
          (do
            (println "Creating new metric row.")
            (db-create-metric!
              {:context context
               :metric_type p-metric-type
               :date_prefix date-prefix
               :dim_name
               (str2num
                 (str (or dim-name1 "")
                      (or dim-name2 "")
                      (or dim-name3 "")
                      (or dim-name4 "")))
               :dim_name1 dim-name1
               :dim_name2 dim-name2
               :dim_name3 dim-name3
               :dim_name4 dim-name4
               :dim_value
               (str2num
                 (str (or dim-value1 "")
                      (or dim-value2 "")
                      (or dim-value3 "")
                      (or dim-value4 "")))
               :dim_value1 dim-value1
               :dim_value2 dim-value2
               :dim_value3 dim-value3
               :dim_value4 dim-value4
               :metric_value p-incr-val
               :updated_at (Date.)}))
          (do
            (println "Incrementing current metric row")
            (db-increment-metric!
              {:metric_id (:id metric)
               :incr_value p-incr-val})))))))
