# https://twitter.com/pepe_planet
# https://twitter.com/pepe_planet/status/1655832948799868929?s=20

from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import sys,time,os,json

from selenium.webdriver.common.by import By
from selenium.common.exceptions import ElementNotVisibleException,ElementNotSelectableException,NoSuchElementException
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

import os

def connect_chrome_driver(debug_port):
    chrome_options = Options()
    chrome_options.add_experimental_option("debuggerAddress", "127.0.0.1:{}".format(debug_port))
    driver = webdriver.Chrome(options=chrome_options)
    
    try:
        driver.minimize_window()
        driver.maximize_window()
    except:
        print("捕捉到最大化最小化浏览器失败，忽略")
    return driver

from twitterlib import *

def follow(driver):
    twitter_name = 'taskonxyz'
    intentFollow(driver, twitter_name)
    twitter_name = 'TaskOnCampaigns'
    intentFollow(driver, twitter_name)


def job(driver, tweet_id, comment=None):
    # comment = f'{getFriends(random.randint(2, 3))} {comment}'
    intentLike(driver, tweet_id)
    intentRetweet(driver, tweet_id)
    # intentPostTweet(driver, comment)
    # intentCommnetTweet(driver, tweet_id, comment)
    # intentQuoteTweet(driver, "https://twitter.com/sui_guide/status/1656887644138409984", comment)


def open_websit(websits):
    for websit in websits:
        try:
            driver.switch_to.new_window('tab')
            driver.get(websit)
            time.sleep(5)
        except Exception as e: 
            print("打开浏览器失败")

# 检查端口是否可用
import socket
import time
def is_port_in_use(port):
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.settimeout(0.01)
        return s.connect_ex(('localhost', port)) == 0


if __name__ == "__main__":
    idx = 0

    with open("auto_port_config.json", 'r') as f:
        portconfig = json.load(f)

    for conf in portconfig:
        if not is_port_in_use(conf[0]):
            continue
        driver = connect_chrome_driver(conf[0])
        driver.switch_to.new_window('tab')
        main_wd_handle = driver.current_window_handle   
        # follow(driver)


        # tasks = ["1659861329988177920"]
        # for tweet_id in tasks:
        #     job(driver, tweet_id, conf[2])


        open_websit(["https://galxe.com/Linea/campaign/GCcr5UNjtY"])
