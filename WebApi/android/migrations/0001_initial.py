# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Class',
            fields=[
                ('classid', models.IntegerField(primary_key=True, serialize=False)),
                ('qrCode', models.IntegerField()),
                ('occurance', models.DateTimeField()),
                ('room', models.CharField(max_length=10)),
                ('building', models.CharField(max_length=20)),
            ],
        ),
        migrations.CreateModel(
            name='Module',
            fields=[
                ('moduleid', models.IntegerField(primary_key=True, serialize=False)),
                ('module_code', models.CharField(max_length=20)),
                ('module_title', models.CharField(max_length=50)),
            ],
        ),
        migrations.CreateModel(
            name='Staff',
            fields=[
                ('staffid', models.IntegerField(primary_key=True, serialize=False)),
                ('email', models.CharField(max_length=60)),
                ('first_name', models.CharField(max_length=30)),
                ('last_name', models.CharField(max_length=30)),
                ('password', models.CharField(max_length=30)),
            ],
        ),
        migrations.CreateModel(
            name='Student',
            fields=[
                ('email', models.CharField(max_length=60)),
                ('matric_number', models.IntegerField(primary_key=True, serialize=False)),
                ('first_name', models.CharField(max_length=30)),
                ('last_name', models.CharField(max_length=30)),
                ('password', models.CharField(max_length=30)),
            ],
        ),
        migrations.AddField(
            model_name='module',
            name='coordinators',
            field=models.ManyToManyField(to='android.Staff'),
        ),
        migrations.AddField(
            model_name='module',
            name='students_enrolled',
            field=models.ManyToManyField(to='android.Student'),
        ),
        migrations.AddField(
            model_name='class',
            name='class_register',
            field=models.ManyToManyField(to='android.Student'),
        ),
    ]
