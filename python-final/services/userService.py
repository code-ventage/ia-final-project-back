import sys
import os
dir_father = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(dir_father)
import json
from exceptions.duplicateUser import DuplicateUser
from repositories.userRepository import UserRepository
from services.service import Service
class UserService(Service):
    def store(username: str, password: str) -> None:
        try:
            UserRepository.store(username, password)
            Service.response['response']['message'] = 'Se ha registrado el usuario correctamente'
        except DuplicateUser as de:
            Service.response['response']['status'] = 401
            Service.response['response']['message'] = str(de)
        except Exception as e:
            Service.response['response']['status'] = 500
            Service.response['response']['message'] = str(e)
        finally:
            with open('response.json', 'w') as file:
                json.dump(Service.response, file, indent=4)
                
    def index(username: str = None) -> list:  
        try:
            response = UserRepository.index()
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
                
    def login(username: str, password: str) -> list:  
        try:
            response = UserRepository.index()
            response = list(filter(lambda a: a['username'] == username and a['password'] == password, response))
            
            if len(response) > 0:
                Service.response['response']['message'] = 'Se ha autentificado correctamente'
                Service.response['response']['data'] = response
            else:
                Service.response['response']['status'] = 422
                Service.response['response']['message'] = 'Credenciales incorrectas'
        except Exception as e:
            Service.response['response']['status'] = 500
            Service.response['response']['message'] = str(e)
        finally:
            with open('response.json', 'w') as file:
                json.dump(Service.response, file, indent=4)
            
        
if __name__ == '__main__':
    # UserService.store('laos', '5')
    UserService.index()
    UserService.index()