(ns analytics.metrics.impl.property
  (:require [analytics.services.metrics :as metrics])
  (:gen-class))

(defn run
  "Number of x per property name"
  [context data]
  (let [props (:properties data)
        prop-keys (keys props)]
    (doseq [k prop-keys]
      (metrics/increment!
        {:context context
         :dim-name1 "site-id"
         :dim-name2 (str "prop--" (name k))
         :dim-value1 (:site-id data)
         :dim-value2 (get props k)}))))
