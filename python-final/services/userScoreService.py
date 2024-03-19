import os
import sys

dir_father = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(dir_father)
import json
from repositories.userScoreRepository import UserScoreRepository
from services.service import Service
from datetime import datetime as dt
from models.userScoreModel import userScoreModel
class UserScoreService(Service):
    def store(data: dict) -> None:        
        try:
            username = data['username']
            score = data['score']
            
            userscore = userScoreModel(username, score)
            UserScoreRepository.store(userscore)
            Service.response['message'] = 'Se ha guardado correctamente la informacion'
            Service.response['response']['data'] = [userscore.toJson()]
        except Exception as e:
            Service.response['response']['status'] = 500
            Service.response['response']['message'] = str(e)
            Service.response['response']['data'] = None
        finally:
            with open('response.json', 'w') as file:
                json.dump(Service.response, file, indent=4)
            Service.resetResponse()
                
    def index(data: dict = None) -> list:                    
        try:
            username = None
            if len(data) > 0:
                username = data['username']
                
            response = UserScoreRepository.index()
            response = [i.toJson() for i in response]
            response.sort(key=lambda x: (-int(x['score']), dt.strptime(x['data'], '%Y-%m-%d %H:%M:%S.%f')))
            if username is None:
                Service.response['response']['data'] = response
            else:
                Service.response['response']['data'] = list(filter(lambda a: a['username'] == username, response))
        except Exception as e:
            Service.response['response']['status'] = 500
            Service.response['response']['message'] = str(e)
        finally:
            with open('response.json', 'w') as file:
                json.dump(Service.response, file, indent=4)    
            Service.resetResponse()        
        
if __name__ == '__main__':
    UserScoreService.store({'username': 'laos', 'score': 55})
    # UserScoreService.index()