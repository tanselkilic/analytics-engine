(ns analytics.core
  (:require [migratus.core :as migratus]
            [clojure.java.jdbc :as jdbc]
            [conman.core :as conman]
            [analytics.env :refer [env]])
  (:import [java.sql BatchUpdateException PreparedStatement])
  (:gen-class))

(def db (atom nil))

(if (nil? @db)
  (do
    (reset! db (conman/connect!
                {:init-size  1
                 :min-idle   1
                 :max-idle   4
                 :max-active 32
                 :jdbc-url   (env :database-url)}))
    (conman/bind-connection
      @db
      "sql/users.sql"
      "sql/sessions.sql"
      "sql/contexts.sql"
      "sql/ops.sql"
      "sql/sites.sql")))


(defn to-date [sql-date]
  (-> sql-date (.getTime) (java.util.Date.)))

(extend-protocol jdbc/IResultSetReadColumn
  java.sql.Date
  (result-set-read-column [v _ _] (to-date v))

  java.sql.Timestamp
  (result-set-read-column [v _ _] (to-date v)))

(extend-type java.util.Date
  jdbc/ISQLParameter
  (set-parameter [v ^PreparedStatement stmt idx]
    (.setTimestamp stmt idx (java.sql.Timestamp. (.getTime v)))))


(defn start-app [args]
  (println "Starting app"))


(defn get-db []
  @db)


(def conn-edn
  {:store :database
   :migration-dir "migrations"
   :db {:classname "com.mysql.jdbc.Driver"
        :subprotocol "mysql"
        :subname (str "//" (env :database-host) "/" (env :database-name))
        :user (env :database-user)
        :useSSL false
        :password (env :database-pass)}})


(defn -main [& args]
  (cond
    (some #{"migrate"} args)
      (migratus/migrate conn-edn)
    (some #{"rollback"} args)
      (migratus/rollback conn-edn)
    :else
    (start-app args)))
