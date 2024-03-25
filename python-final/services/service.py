from abc import ABC, abstractmethod

class Service(ABC):
    """
    Clase abstracta Service.

    Atributos:
        response (dict): Respuesta predeterminada.

    Métodos:
        store() -> None:
            Método abstracto para almacenar datos.

        index(username: str = None) -> list:
            Método abstracto para obtener una lista de datos.

        resetResponse() -> None:
            Restablece la respuesta a su valor predeterminado.
    """
    response = {
        'version': 0,
        'response':
            {
                'status': 200, 'message': '', 'data': None
            }
    }
                
    @staticmethod
    @abstractmethod
    def store() -> None:
        """
        store() -> None:
            Método abstracto para almacenar datos.
        """
        pass
    @staticmethod
    @abstractmethod
    def index(username: str = None) -> list:
        """
        index(username: str = None) -> list:
            Método abstracto para obtener una lista de datos.

        :param username: Nombre de usuario opcional.
        :return: Una lista de datos.
        """
        pass
    
    @staticmethod
    def resetResponse() -> None:
        """
        resetResponse() -> None:
            Restablece la respuesta a su valor predeterminado.
        """
        Service.response = {
            'version': 0,
            'response':
                {
                    'status': 200, 'message': '', 'data': None
                }
        }