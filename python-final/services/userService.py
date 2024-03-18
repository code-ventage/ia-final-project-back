import os
import sys

dir_father = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(dir_father)
import json
from exceptions.duplicateUser import DuplicateUser
from repositories.userRepository import UserRepository
from services.service import Service
from models.userModel import userModel
class UserService(Service):
    def store(username: str, password: str) -> None:
        try:
            UserRepository.store(userModel(username, password))
            Service.response['response']['message'] = 'Se ha registrado el usuario correctamente'
        except DuplicateUser as de:
            Service.response['response']['status'] = 422
            Service.response['response']['message'] = str(de)
        except Exception as e:
            Service.response['response']['status'] = 500
            Service.response['response']['message'] = str(e)
        finally:
            with open('response.json', 'w') as file:
                json.dump(Service.response, file, indent=4)
                
            Service.resetResponse()
                
    def index(username: str = None) -> list:  
        try:
            response = UserRepository.index()
            
            if username is None:
                Service.response['response']['data'] = [i.toJson() for i in response]
            else:
                Service.response['response']['data'] = [i.toJson() for i in response if i.username == username]
        except Exception as e:
            Service.response['response']['status'] = 500
            Service.response['response']['message'] = str(e)
        finally:
            with open('response.json', 'w') as file:
                json.dump(Service.response, file, indent=4)
            Service.resetResponse()
                
    def login(username: str, password: str) -> list:  
        try:
            response = UserRepository.index()
            response = [i.toJson() for i in response if i.username == username and i.password == password]
            
            if len(response) > 0:
                Service.response['response']['message'] = 'Se ha autentificado correctamente'
                Service.response['response']['data'] = response
            else:
                Service.response['response']['status'] = 401
                Service.response['response']['message'] = 'Credenciales incorrectas'
                Service.response['response']['data'] = None
        except Exception as e:
            Service.response['response']['status'] = 500
            Service.response['response']['message'] = str(e)
        finally:
            with open('response.json', 'w') as file:
                json.dump(Service.response, file, indent=4)
            Service.resetResponse()
            
        
if __name__ == '__main__':
    UserService.store('test2', '5')
    # UserService.index('laos')
    # UserService.login('laos', 'ad')