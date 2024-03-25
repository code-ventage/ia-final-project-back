import os
import sys

dir_father = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(dir_father)

from repositories.repository import Repository
from exceptions.duplicateUser import DuplicateUser
import json
from models.userModel import userModel
class UserRepository(Repository):
    """
    Clase UserRepository que hereda de Repository.

    Métodos:
        store(user: userModel) -> None:
            Almacena un usuario en el archivo 'users.json'.

        index() -> tuple:
            Obtiene una lista de usuarios del archivo 'users.json'.
    """
    def store(user: userModel) -> None:
        """
        store(user: userModel) -> None:
            Almacena un usuario en el archivo 'users.json'.

        :param user: El usuario a almacenar.
        :raises DuplicateUser: Si el usuario ya existe en el archivo.
        """
        try:
            with open('users.json', 'r') as file:
                
                users = UserRepository.index()
                
                if user in users:
                    raise DuplicateUser() 
        except FileNotFoundError as e:
            users = []

        users.append(user)
        usersJson = []
        
        for i in users:
            usersJson.append(i.toJson())
        
        with open('users.json', 'w') as file:
            json.dump(usersJson, file, indent=4)

    
    def index() -> tuple:
        """
        index() -> tuple:
            Obtiene una lista de usuarios del archivo 'users.json'.

        :return: Una lista de usuarios.
        """
        users = []
        try:
            with open('users.json', 'r') as file:
                for i in json.load(file):
                    user = userModel()
                    user.reverseJson(i)
                    users.append(user)
        except FileNotFoundError as e:
            pass
                        
        return users
                    
if __name__ == '__main__':
    UserRepository.store(userModel('kratos', 'sf'))
    for i in UserRepository.index():
        print(i.username)