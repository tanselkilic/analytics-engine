(ns analytics.services.contexts-test
  (:require [clojure.test :refer :all]
            [analytics.services.contexts :refer :all]
            [analytics.utils :as util]))


(def site-id "914b36e4-a10d-4e13-bf35-df888bfcb1fa")
(def user-id "hebelek666@pismail.com")
(def session-id "session-id")
(def op-id 1)

(deftest add-property-context-test
  (testing "FIXME, I fail."
    (is (= 1
           (add-property-context!
             op-id "page"
             site-id user-id
             session-id "Sign Up" nil
             "url" "/index.html")))))


(deftest add-location-context-test
  (testing "FIXME, I fail."
    (is (= 1
           (add-location-context!
             op-id "page"
             site-id user-id
             session-id "Sign Up" "Sign Up Clicked"
             49 21 "TR" "Istanbul" "34758"
             "")))))


(deftest add-app-context-test
  (testing "FIXME, I fail."
    (is (= 1
           (add-app-context!
             op-id "page"
             site-id user-id
             session-id "Sign Up" nil
             "Kuzu Analytics" "0.0.1")))))


(deftest add-campaign-context-test
  (testing "FIXME, I fail."
    (is (= 1
           (add-campaign-context!
             op-id "page"
             site-id user-id
             session-id "Sign Up" nil
             "Campaign Name" "Source"
             "Medium" "Term" "Content")))))


(deftest add-device-context-test
  (testing "FIXME, I fail."
    (is (= 1
           (add-device-context!
             op-id "page"
             site-id user-id
             session-id "Sign Up" "Sign Up Clicked"
             "device id" "manufacturer"
             "model" "device name")))))


(add-device-context!
             op-id "page"
             site-id user-id
             session-id "Sign Up" "Sign Up Clicked"
             "device id" "manufacturer"
             "model" "device name")


(deftest add-network-context-test
  (testing "FIXME, I fail."
    (is (= 1
           (add-network-context!
             op-id "page"
             site-id user-id
             session-id "Sign Up" nil
             "Turkcell" "Superonline"
             true true false)))))


(deftest add-screen-context-test
  (testing "FIXME, I fail."
    (is (= 1
           (add-screen-context!
             op-id "page"
             site-id user-id
             session-id "Sign Up" "Clicked"
             1024 768)))))


(deftest add-page-context-test
  (testing "FIXME, I fail."
    (is (= 1
           (add-page-context!
             op-id "page"
             site-id user-id
             session-id "Sign Up" "Clicked"
             "external" "http://pismail.com"
             "pismail.com" "Hebelek Title"
             "http://abc.com/index.html")))))
