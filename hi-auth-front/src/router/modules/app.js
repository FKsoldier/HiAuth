import Layout from '@/views/layout/Layout'

const appRouter = {
  path: '/app',
  component: Layout,
  redirect: 'noredirect',
  name: 'AppManager',
  meta: {
    title: 'appManager',
    icon: 'table',
    roles: ['ROLE_ADMIN']
  },
  children: [
    {
      path: 'app-list',
      component: () => import('@/views/app/appList'),
      name: 'AppList',
      meta: { title: 'appList' }
    },
    {
      path: 'clientsUser',
      component: () => import('@/views/clients/appList'),
      name: 'clientsUser',
      meta: { title: 'clientsUser' }
    },
    {
      path: 'clientsUserList',
      component: () => import('@/views/clients/userList'),
      name: 'clientsUserList',
      meta: { title: 'clientsUserList' },
      hidden: true
    }
  ]
}
export default appRouter
