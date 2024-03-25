from abc import ABC
class Model(ABC):
    """
     Clase abstracta Model.

     """
    def toJson(self) -> dict:
        """
        toJson(self) -> dict:
             Convierte los atributos del objeto a un diccionario.

        :return: el diccionario resultante
        """
        attributes = [attr for attr in dir(self) if not callable(getattr(self, attr)) and not attr.startswith("__")]
        response = {}
        for i in range(1, len(attributes)):
            response[attributes[i].lstrip('_')] = getattr(self, attributes[i])             
        return response
    
    def reverseJson(self, json: dict) -> None:
        """
        reverseJson(self, json: dict) -> None:
             Establece los atributos del objeto a partir de un diccionario.
        :param json:
        :return: void
        """
        for k, v in json.items():
            setattr(self, k, v)