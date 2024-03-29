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
    """
    Clase UserService que hereda de Service.

    Métodos:
        store(data: list) -> None:
            Almacena un usuario.

        index(data: list = None) -> list:
            Obtiene una lista de usuarios.

        login(data: list) -> list:
            Autentifica a un usuario.
    """
    def store(data: list) -> None:
        """
        store(data: list) -> None:
            Almacena un usuario.

        :param data: Lista con la información del usuario.
        """
        try:
            username = data['username']
            password = data['password']
            
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
                
    def index(data: list = None) -> list:
        """
        index(data: list = None) -> list:
            Obtiene una lista de usuarios.

        :param data: Lista con la información del usuario opcional.
        :return: Una lista de usuarios.
        """
        try:
            username = None
            if data is not None:
                username = data['username']
                
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
                
    def login(data: list) -> list:
        """
        login(data: list) -> list:
            Autentifica a un usuario.

        :param data: Lista con la información del usuario.
        :return: Una lista con la información del usuario autentificado.
        """
        try:
            response = UserRepository.index()
            response = [i.toJson() for i in response if i.username == data['username'] and i.password == data['password']]
            
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