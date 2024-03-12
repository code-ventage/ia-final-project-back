import sys
import os
dir_father = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(dir_father)
import json
from exceptions.duplicateUser import DuplicateUser
from repositories.userScoreRepository import UserScoreRepository
from services.service import Service
from datetime import datetime as dt

class UserScoreService(Service):
    def store(username: str, score: int) -> None:
        try:
            data = str(dt.now())
            UserScoreRepository.store(username, score, data)
            Service.response['message'] = 'Se ha guardado correctamente la informacion'
            Service.response['response']['data'] = {'username': username, 'score': score, 'data': data}
        except Exception as e:
            Service.response['response']['status'] = 500
            Service.response['response']['message'] = str(e)
        finally:
            with open('python-final/response.json', 'w') as file:
                json.dump(Service.response, file, indent=4)
                
    def index(username: str = None) -> list:        
        try:
            response = UserScoreRepository.index()
            response.sort(key=lambda x: (-int(x['score']), dt.strptime(x['data'], '%Y-%m-%d %H:%M:%S.%f')))
            if username is None:
                Service.response['response']['data'] = response
            else:
                Service.response['response']['data'] = list(filter(lambda a: a['username'] == username, response))
        except Exception as e:
            Service.response['response']['status'] = 500
            Service.response['response']['message'] = str(e)
        finally:
            with open('python-final/response.json', 'w') as file:
                json.dump(Service.response, file, indent=4)            
        
if __name__ == '__main__':
    input()
    # UserScoreService.store('laos', 5)
    UserScoreService.index()