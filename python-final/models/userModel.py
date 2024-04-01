import os
import sys

dir_father = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(dir_father)

from models.model import Model

class userModel(Model):
    """
    Clase userModel que hereda de Model.

    Atributos:
        _username (str): Nombre de usuario.
        _password (str): Contraseña del usuario.
    """
    def __init__(self, username: str = None, password: str =  None) -> None:
        super().__init__()
        self._username = username
        self._password = password
        
    def __eq__(self, __value: object) -> bool:
        """
        __eq__(self, __value: object) -> bool:
            Compara si el objeto proporcionado es igual a este objeto.

        :param __value:
        :return: boolean
        """
        if type(__value) == userModel :
            if __value.username == self.username:
                return True
            
        return False
        
    @property
    def username(self) -> str:
        """
        username(self) -> str:
            Obtiene el nombre de usuario.

        :return: el nombre del usuario
        """
        return self._username
    @username.setter
    def username(self, username: str) -> None:
        """
         username(self, username: str) -> None:
            Establece el nombre de usuario.

        :param username:
        :return: void
        """
        self._username = username
        
    @property
    def password(self) -> str:
        """
           password(self) -> str:
            Obtiene la contraseña del usuario.

        :return: la contraseña del usuario
        """
        return self._password
    @password.setter
    def password(self, password: str) -> None:
        """
          password(self, password: str) -> None:
            Establece la contraseña del usuario.
        :param password:
        :return: void
        """
        self._password = password

if '__main__' == __name__:
    j = userModel('laos', '5')
    print(j.toJson())
    i = userModel()
    i.reverseJson(j)