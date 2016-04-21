(ns analytics.services.users-test
  (:require [clojure.test :refer :all]
            [analytics.services.users :refer :all]
            [analytics.services.sites :refer :all]
            [analytics.utils :as util]))


(def site-id (util/uuid))
(def site-name "Hebelek")
(def site-description "Hebelek Site")
(def site-domain "http://www.pismail.com")
(def anonymous-id (util/uuid))


(defn tear-down []
  (delete-user-by-anon-id! anonymous-id)
  (delete-site-by-id! site-id))


(defn add-site-test []
  (add-site! site-id site-name
            site-description site-domain))

(deftest add-user-test
  (testing "FIXME, I fail."
    (add-site-test)
    (is (= 1 (add-user!
               anonymous-id
               nil
               site-id
               "mobile")))))


(deftest identify-user-test
  (testing "FIXME, I fail."
    (add-site-test)
    (add-user! anonymous-id nil site-id "mobile")
    (identify-user! anonymous-id "umutgokbayrak@yahoo.com")))


(defn my-test-fixture [f]
  (f)
  (tear-down))

(use-fixtures :each my-test-fixture)
