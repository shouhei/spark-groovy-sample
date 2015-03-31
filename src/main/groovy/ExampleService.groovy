import static spark.Spark.*
import groovy.json.JsonBuilder

class ExampleService {
  public static void main(String[] args) {

    Object.metaClass.asJson = {
      def builder = new JsonBuilder(delegate);
      builder.toString()
    }

    def obj = new A();

    def html = """
    <!DOCTYPE html>
    <html>
      <head>
        <title>hallo spark with groovy</title>
      </head>
      <body>
        <p>hallo spark with groovy</p>
      </body>
    </html>
    """

    def map = [:]
    map['foo'] = 'Hello!!!'
    map['bar'] = [1, 2, 3]
    map['boo'] = ['hoge' : 'aaa', 'fuga' : [1, 2, ['obj':obj]]]
    get('/',{req, res -> html})
    get('/obj', { req, res -> obj.asJson() })
    get('/map', { req, res -> map.asJson() })

  }
}
