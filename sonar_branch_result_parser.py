#!/usr/bin/env python

import json
import requests
import sys


def getSonarResult(data, branch):
    for item in data["branches"]:
        if (item["name"] == branch):
            result = item["status"]["qualityGateStatus"]
            # print(item["name"] + ' -> ' + result)
            sys.stdout.write(result)
            sys.stdout.flush()
            sys.exit(0)

def requestQualityGateJson(url):
    r = requests.get(url)
    if (r.status_code == requests.codes.ok):
        return json.loads(r.content)

# execute
url = sys.argv[1]
branch = sys.argv[2]


qualityGateJson = requestQualityGateJson(url)
getSonarResult(qualityGateJson, branch)