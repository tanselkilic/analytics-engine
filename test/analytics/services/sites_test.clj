(ns analytics.services.sites-test
  (:require [clojure.test :refer :all]
            [analytics.services.sites :refer :all]
            [analytics.utils :as util]))


(def site-id (util/uuid))
(def site-name "Hebelek")
(def site-description "Hebelek Site")
(def site-domain "http://www.pismail.com")

(deftest add-site-test-by-id
  (testing "FIXME, I fail."
    (is (= 1 (add-site!
               site-id
               site-name
               site-description
               site-domain)))))


(deftest add-site-test-by-no-id
  (testing "FIXME, I fail."
    (is (= 1 (add-site!
               nil
               site-name
               site-description
               site-domain)))))


(defn my-test-fixture [f]
  (f)
  (delete-site-by-id! site-id))

(use-fixtures :each my-test-fixture)
