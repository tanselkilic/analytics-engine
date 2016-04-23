(ns analytics.api.core-test
  (:require [clojure.test :refer :all]
            [analytics.api.core :refer :all]))


(def data {:anonymous-id "ca5bd8b1-f028-4697-9bfb-47ef33740f51"
           :type "event"
           :site-id "914b36e4-a10d-4e13-bf35-df888bfcb1fa"
           :user-id "hebelek666@pismail.com"
           :session-id "session-id"
           :channel "fn"
           :page "Sample"
           :event "Button Clicked"
           :context {:app {:name "Initech Global"
                           :version "1.0"}
                     :campaign {:name "TPS Innovation Newsletter"
                                :source "Newsletter"
                                :medium "email"
                                :term "tps records"
                                :content "image link"}
                     :device {:id "B5372DB0-C21E-11E4-8DFC-AA07A5B093DB"
                              :manufacturer "Apple"
                              :model "iPhone7,2"
                              :name "maguro"
                              :type "ios"}
                     :locale "nl-NL"
                     :location {:city "San Francisco"
                                :country "United States"
                                :latitude 40.2964197
                                :longitude -76.9411617}
                     :ip "8.8.8.8"
                     :network {:bluetooth false
                               :carrier "T-Mobile NL"
                               :cellular true
                               :wifi true}
                     :os {:name "iPhone OS"
                          :version "8.1.3"}
                     :page {:url "/academy/"
                            :referrer {:type "external"
                                       :url "http://pismail.com/index"
                                       :domain "pismail.com"}
                            :title "Academy Page Title"}
                     :screen {:height 768
                              :width 1024}
                     :user-agent "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1"
                     :timezone "3" ;; var hrs = -(new Date().getTimezoneOffset() / 60)
                     :hebelek {:test "1"}}
           :properties {}
           })

(deftest track-test
  (track data))

(track-test)
