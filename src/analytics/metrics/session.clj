(ns analytics.metrics.session
  (:require [analytics.services.metrics :as metrics])
  (:gen-class))

(defn metrics-ops [data]
  (println "Got a session metric" data)

  ;; number of sessions per channel
  (metrics/increment!
    {:context "session"
     :dim-name1 "site-id"
     :dim-name2 "channel"
     :dim-value1 (:site-id data)
     :dim-value2 (:channel data)})

  ;; number of sessions per site
  (metrics/increment!
    {:context "session"
     :dim-name1 "site-id"
     :dim-value1 (:site-id data)})

  ;; avg number of sessions per user (v/uv)

  ;; number of sessions per app

  ;; number of sessions per campaign

  ;; number of sessions per device

  ;; number of sessions per locale
  (if-let [locale (:locale (:context data))]
    (metrics/increment!
      {:context "session"
       :dim-name1 "site-id"
       :dim-name2 "locale"
       :dim-value1 (:site-id data)
       :dim-value2 locale}))

  ;; number of sessions per location

  ;; number of sessions per network

  ;; number of sessions per os

  ;; number of sessions per referrer

  ;; number of sessions per screen

  ;; number of sessions per timezone

  ;; number of sessions per property

  )

