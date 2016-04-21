(ns analytics.core-test
  (:require [clojure.test :refer :all]
            [analytics.core :refer :all]))

(deftest conn-test
  (testing "Connection is nil"
    (is (some? (get-db)))))
