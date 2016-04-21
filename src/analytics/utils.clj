(ns analytics.utils
  (:import [java.util UUID])
  (:gen-class))


(defn uuid []
  (.toString (UUID/randomUUID)))
