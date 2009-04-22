from zohmg.utils import fail
import os, sys


DIRS = ["config","lib","mappers","transformers"]

# config/environment.py
ENV_SCRIPT = """# Please define the following environment variables.

# Path to Hadoop directory.
HADOOP_HOME = ''

# Path to HBase directory.
HBASE_HOME = ''

# Jars for Hadoop Core, Hadoop Streaming and HBase
CLASSPATH = (
  # be sure to set these correctly!
  '/home/hadoop/hadoop-0.19/build/hadoop-0.19.0-dev-core.jar',
  '/home/hadoop/hadoop-0.19/build/contrib/streaming/hadoop-0.19.0-dev-streaming.jar',
  '/home/hadoop/hbase-0.19/build/hbase-0.19.0-dev.jar'
)
"""

README = """This is your zohmg application!

Configure dataset.yaml to match your data,
run 'zohmg setup' to create an hbase table.

Then write a mapper and run it with 'zohmg import'.

Take a look in /usr/local/share/zohmg for further documentation.
"""

MAPPER = """# identity mapper.
def map(key, value):
    yield key, value
"""

class Create(object):
    def __init__(self, path):
        self.basename = os.path.basename(path)
        self.abspath  = os.path.abspath(path)

        print "Creating %s" % self.basename

        # Create project directories with 0755.
        try:
            os.mkdir(self.abspath)
            for dir in DIRS:
                os.mkdir(self.abspath+"/"+dir)
        # Something went wrong, act accordingly.
        except OSError, ose:
            msg = "Error: Could not create project directories. %s" % ose.strerror
            fail(msg, ose.errno)

        # Create .zohmg, README, mapper, env.
        self.__write_to_file('.zohmg')
        self.__write_to_file('README', README)
        self.__write_to_file('mappers/identity_mapper.py', MAPPER)
        self.__write_to_file('config/environment.py', ENV_SCRIPT)

        # Create skeleton config/dataset.yaml
        datasetconfig = "project_name: %s\n" % self.basename \
                      + "dimensions:\n  -d0\n  -d1\n" \
                      + "projections:\n  p0:\n    -d0\n    -d1\n" \
                      + "units:\n  u0\n"
        self.__write_to_file("config/dataset.yaml", datasetconfig)
        # TODO: consider using Config.config_file instead of hardcoded path.

        print "ok."


    def __write_to_file(self, filename, contents = ''):
        file = self.abspath + '/' + filename
        try:
            f = open(file, "w")
            f.write(contents)
            f.close()
        except IOError, ioe:
            msg = "Kernel malfunction: %s (%s)." % (ioe.strerror, file)
            fail(msg, ioe.errno)
