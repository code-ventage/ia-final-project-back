import json

from services.service import Service
from services.userScoreService import UserScoreService
from services.userService import UserService

try:
    with open('request.json', 'r') as file:
        request = json.load(file)
        Service.response['version'] = request['version']
            
        getattr(globals()[request['service']], request['method'])(request['data'])

except Exception as e:
    Service.response['response']['status'] = 500
    Service.response['response']['message'] = str(e)
    with open('response.json', 'w') as file:
        json.dump(Service.response, file, indent=4)
finally:
    Service.resetResponse()