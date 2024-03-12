import json

from services.service import Service
from services.userScoreService import UserScoreService
from services.userService import UserService

try:
    with open('request.json', 'r') as file:
        request = json.load(file)
        Service.response['version'] = request['version']

        if request['service'] == 'userService':
            if request['method'] == 'store':
                UserService.store(request['data']['username'], request['data']['password'])
            elif request['method'] == 'index':
                if request['data'] is None:
                    UserService.index()
                else:
                    UserService.index(request['data']['username'])
            elif request['method'] == 'login':
                UserService.login(request['data']['username'], request['data']['password'])
        if request['service'] == 'userScoreService':
            if request['method'] == 'store':
                UserScoreService.store(request['data']['username'], request['data']['score'])
            elif request['method'] == 'index':
                if request['data'] is None:
                    UserScoreService.index()
                else:
                    UserScoreService.index(request['data']['username'])
except Exception as e:
    Service.response['response']['status'] = 500
    Service.response['response']['message'] = str(e)
    with open('response.json', 'w') as file:
        json.dump(Service.response, file, indent=4)