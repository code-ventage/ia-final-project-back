from abc import ABC, abstractmethod
class Repository(ABC):
    """
    Clase abstracta Repository.

    Métodos:
        store() -> None:
            Método abstracto para almacenar datos.

        index() -> list:
            Método abstracto para obtener una lista de datos.
    """
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
    def index() -> list:
        """
        index() -> list:
            Método abstracto para obtener una lista de datos.

        :return: una lista de datos
        """
        pass