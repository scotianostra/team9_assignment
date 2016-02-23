# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('android', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='module',
            name='coordinators',
            field=models.ManyToManyField(related_name='modules', to='android.Staff'),
        ),
    ]
