import os
import sys

dir_father = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(dir_father)

from models.model import Model
from datetime import datetime as dt


class userScoreModel(Model):
    """
        Clase userScoreModel que hereda de Model.

        Atributos:
           _username (str): Nombre de usuario.
           _score (int): Puntuación del usuario.
           _date (str): Fecha y hora de la puntuación.

        Métodos:
           __init__(self, username: str = None, score: int = None) -> None:
               Inicializa un objeto de la clase userScoreModel.

           username(self) -> str:
               Obtiene el nombre de usuario.

           username(self, username: str) -> None:
               Establece el nombre de usuario.

           score(self) -> int:
               Obtiene la puntuación del usuario.

           score(self, score: int) -> None:
               Establece la puntuación del usuario.
    """

    def __init__(self, username: str = None, score: int = None) -> None:
        """
        __init__(self, username: str = None, score: int = None) -> None:
            Inicializa un objeto de la clase userScoreModel.

        :param username: Nombre de usuario.
        :param score: Puntuación del usuario.
        """
        super().__init__()
        self._username = username
        self._score = score
        self._date = str(dt.now())

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

        :param username: Nombre de usuario.
        """
        self._username = username

    @property
    def score(self) -> int:
        """
        score(self) -> int:
            Obtiene la puntuación del usuario.

        :return: la puntuación del usuario
        """
        return self._score

    @score.setter
    def score(self, score: int) -> None:
        """
        score(self, score: int) -> None:
            Establece la puntuación del usuario.

        :param score: Puntuación del usuario.
        """
        self._score = score


if '__main__' == __name__:
    j = userScoreModel('laos', 5)
    print(j.toJson())