(ns analytics.env
  (:use [clojure.java.io])
  (:gen-class))


(def env-var (or (System/getenv "ENV") "dev"))
(def config-map (load-file (.getFile (resource "config.edn"))))

(defn env [k]
  (get (get config-map (keyword env-var)) k))
